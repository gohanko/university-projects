import express from 'express'
import {
    registerUser,
    loginUser,
    logoutUser,
    changeUserPassword
} from '../controllers/user.controller'
import { body } from 'express-validator'
import inputValidation from '../middlewares/inputValidation.middleware'
import { mustBeAuthorized } from '../middlewares/mustBeAuthorized.middleware'

const userRouter = express.Router()

userRouter.post(
    '/user/register/',
    body('email')
        .notEmpty()
        .isEmail()
        .withMessage("Email is invalid."),
    body('password')
        .notEmpty()
        .isString()
        .withMessage("Password is invalid."),
        inputValidation,
        registerUser
)

userRouter.post(
    '/user/login/',
    body('email')
        .notEmpty()
        .isEmail()
        .withMessage("Email is invalid."),
    body('password')
        .notEmpty()
        .isString()
        .withMessage("Password is invalid."),
    inputValidation,
    loginUser
)

userRouter.get(
    '/user/logout/',
    inputValidation,
    mustBeAuthorized,
    logoutUser
)

userRouter.post(
    '/user/change_password/',
    body("new_password")
        .notEmpty()
        .isString()
        .withMessage("new_message must not be empty."),
    inputValidation,
    mustBeAuthorized,
    changeUserPassword
)

export default userRouter