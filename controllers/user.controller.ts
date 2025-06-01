import { Request, Response, NextFunction } from "express"
import databaseService from "../services/database.service"
import { generateAccessToken } from '../utils/generateAccessToken.util'

const User = databaseService.user
const TokenBlacklist = databaseService.tokenBlacklist

export const registerUser = async (
    req: Request,
    res: Response,
) => {
    const { email, password } = req.body;

    try {
        const existingUser = await User.findOne({ where: { email: email } })
        if (existingUser) {
            return res
                .status(400)
                .json({
                    status: "failed",
                    message: "It seems you already have an account, please log in instead.",
                })
        }

        const newUser = await User.create({
            email,
            password,
        })

        const { role, ...userData } = newUser.dataValues
        res.status(201).json({
            status: "success",
            data: [userData],
            message: "Thank you for registering with us. Your account has been successfully created."
        })
    } catch (err) {
        res.status(500).json({
            status: "error",
            code: 500,
            message: "Internal Server Error",
        });
    }

    res.end()
}

export const loginUser = async (
    req: Request,
    res: Response,
) => {
    const { email, password } = req.body;

    try {
        const existingUser = await User.findOne({ where: { email: email } })
        if (!existingUser) {
            return res.status(400).json({
                status: "failed",
                message: "User does not exist."
            })
        }

        if (existingUser?.dataValues.email !== email
            || existingUser?.dataValues.password !== password) {
            return res.status(400).json({
                status: "failed",
                message: "Invalid username or password."
            })
        }

        const token = generateAccessToken(existingUser?.dataValues.id)
        const options = {
            maxAge: 20 * 60 * 1000, // would expire in 20 minutes
            httpOnly: true, // The cookie is only accessible by the web server
            secure: true,
            sameSite: true,
        };

        res.cookie("SessionID", token, options)
        return res.status(200).json({
            status: "success",
            accessToken: token,
            message: "You have successfully logged in."
        })
    } catch (err) {
        res.status(500).json({
            status: "error",
            code: 500,
            message: `Internal Server Error: ${err}`,
        });
    }

    res.end()
}

export const logoutUser = async (
    req: Request,
    res: Response,
) => {
    try {
        const authHeader = req.headers['cookie'];
        if (!authHeader) {
            return res.sendStatus(204);
        }

        const cookie = authHeader.split('=')[1];
        const accessToken = cookie.split(';')[0];
        const checkIfBlacklisted = await TokenBlacklist.findOne({ where: { token: accessToken }});
        if (checkIfBlacklisted) {
            return res.sendStatus(204);
        }

        await TokenBlacklist.create({
            token: accessToken,
        });

        res.setHeader('Clear-Site-Data', '"cookies"');
        res.status(200).json({ message: 'You are logged out!' });
    } catch (err) {
        res.status(500).json({
            status: 'error',
            message: 'Internal Server Error',
        });
    }

    res.end();
}

export const changeUserPassword = async (
    req: Request,
    res: Response,
) => {
    const { new_password } = req.body

    const userId = req.user.id
    const user = await User.findByPk(userId)
    user?.update({ password: new_password})

    return res.status(200).json({
        status: 'success',
        message: "User password has been updated."
    })
}
