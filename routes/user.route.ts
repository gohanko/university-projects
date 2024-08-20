import express from 'express'
import {
    registerUser,
    loginUser,
    logoutUser,
    changeUserPassword
} from '../controllers/user.controller'
import { check } from 'express-validator'
import inputValidation from '../middlewares/inputValidation.middleware'
import { mustBeAuthorized } from '../middlewares/mustBeAuthorized.middleware'

const userRouter = express.Router()

userRouter.use(inputValidation)

userRouter.post(
    '/user/register/',
    check('email')
        .notEmpty()
        .isEmail()
        .withMessage("Email is invalid."),
    check('password')
        .notEmpty()
        .isString()
        .withMessage("Password is invalid."),
        registerUser
)

userRouter.post(
    '/user/login/',
    check('email')
        .notEmpty()
        .isEmail()
        .withMessage("Email is invalid."),
    check('password')
        .notEmpty()
        .isString()
        .withMessage("Password is invalid."),
    loginUser
)

userRouter.post(
    '/user/logout/',
    logoutUser
)

userRouter.post(
    '/user/change_password/',
    check("new_password")
        .notEmpty()
        .isString()
        .withMessage("new_message must not be empty."),
    mustBeAuthorized,
    changeUserPassword
)

export default userRouter