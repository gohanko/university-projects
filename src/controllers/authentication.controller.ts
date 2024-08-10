import { Request, Response, NextFunction } from "express"
import databaseService from "../services/database.service"
import { generateAccessToken } from '../utils/'

const User = databaseService.user
const TokenBlacklist = databaseService.tokenBlacklist

const register = async (
    req: Request,
    res: Response,
    next: NextFunction
) => {
    const { email, password } = req.body;

    if (!email) {
        res.status(400).json({
            status: "Failed",
            message: "Email are required.",
            data: [],
        })
    }

    if (!password) {
        res.status(400).json({
            status: "Failed",
            message: "Password are required.",
            data: [],
        })
    }

    try {
        const existingUser = await User.findOne({ where: { email: email } })
        if (existingUser) {
            return res
                .status(400)
                .json({
                    status: "Failed",
                    data: [],
                    message: "It seems you already have an account, please log in instead.",
                })
        }

        const newUser = await User.create({
            email,
            password,
        })

        const { role, ...userData } = newUser.dataValues
        res.status(201).json({
            status: "Success",
            data: [userData],
            message: "Thank you for registering with us. Your account has been successfully created."
        })
    } catch (err) {
        res.status(500).json({
            status: "error",
            code: 500,
            data: [],
            message: "Internal Server Error",
        });
    }

    res.end()
}

const login = async (
    req: Request,
    res: Response,
    next: NextFunction
) => {
    const { email, password } = req.body;

    if (!email) {
        res.status(400).json({
            status: "Failed",
            data: [],
            message: "Email are required.",
        })
    }

    if (!password) {
        res.status(400).json({
            status: "Failed",
            data: [],
            message: "Password are required.",
        })
    }

    try {
        const existingUser = await User.findOne({ where: { email: email } })
        if (!existingUser) {
            res.status(400).json({
                status: "Failed",
                data: [],
                message: "User does not exist."
            })
        }

        if (existingUser?.dataValues.email == email && existingUser?.dataValues.password == password) {
            const token = generateAccessToken(existingUser?.dataValues.id)
            const options = {
                maxAge: 20 * 60 * 1000, // would expire in 20 minutes
                httpOnly: true, // The cookie is only accessible by the web server
                secure: true,
                sameSite: true,
            };

            res.cookie("SessionID", token, options)
            res.status(200).json({
                status: "Success",
                data: [],
                message: "You have successfully logged in."
            })
        } else {
            res.status(400).json({
                status: "Failed",
                data: [],
                message: "Invalid username or password."
            })
        }
    } catch (err) {
        res.status(500).json({
            status: "error",
            code: 500,
            data: [],
            message: "Internal Server Error",
        });
    }

    res.end()
}

const logout = async (
    req: Request,
    res: Response,
    next: NextFunction
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

const resetPassword = (
    req: Request,
    res: Response,
    next: NextFunction
) => {
}

export {
    register,
    login,
    logout,
    resetPassword
}