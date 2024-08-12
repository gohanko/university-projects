import express from 'express'
import {
    register,
    login,
    logout,
    changePassword
} from '../controllers/user.controller'
import { check } from 'express-validator'
import inputValidation from '../middlewares/inputValidation.middleware'
import verifyToken from '../middlewares/verifyToken.middleware'

const userRouter = express.Router()
userRouter.post(
    '/user/register/',
    check('email').notEmpty().isEmail().withMessage("Email is invalid."),
    check('password').notEmpty().isString().withMessage("Password is invalid."),
    inputValidation,
    register
)

userRouter.post(
    '/user/login/',
    check('email').notEmpty().isEmail().withMessage("Email is invalid."),
    check('password').notEmpty().isString().withMessage("Password is invalid."),
    inputValidation,
    login
)

userRouter.post(
    '/user/logout/',
    logout
)

userRouter.post(
    '/user/change_password/',
    verifyToken,
    changePassword
)

export default userRouter