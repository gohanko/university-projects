import { NextFunction, Request, Response } from "express";
import databaseService from "../services/database.service";
import jwt from 'jsonwebtoken'
import { SECRET_ACCESS_TOKEN } from "../configs";

const User = databaseService.user
const TokenBlacklist = databaseService.tokenBlacklist

const mustBeAuthorized = async (
    req: Request,
    res: Response,
    next: NextFunction
) => {
    try {
        const authHeader = req.headers["cookie"];
        if (!authHeader) {
            return res.sendStatus(401);
        }

        const accessToken = authHeader.split("=")[1].split(';')[0];
        const checkIfBlacklisted = await TokenBlacklist.findOne({ where: { token: accessToken }});
        if (checkIfBlacklisted) {
            return res
                .status(401)
                .json({ message: "This session has expired. Please login" });
        }

        jwt.verify(
            accessToken,
            SECRET_ACCESS_TOKEN as string,
            async (err, decoded) => {
                if (err) {
                    return res
                        .status(401)
                        .json({ message: "This token is invalid." });
                }

                const { id }: any = decoded;            
                const user = await User.findByPk(id);
                const {
                    password,
                    ...data
                } = user?.dataValues;
                
                req.user = data;
                next();
            }
        );
    } catch (err) {
        res.status(500).json({
            status: "error",
            code: 500,
            message: "Internal Server Error",
        });
    }
}

export {
    mustBeAuthorized
}