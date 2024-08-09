import express from 'express'
import {
    register,
    login,
    logout,
    resetPassword
} from '../controllers/authentication.controller'

const authenticationRouter = express.Router()

authenticationRouter.get('/register/', register)
authenticationRouter.get('/login/', login)
authenticationRouter.get('/logout/', logout)
authenticationRouter.get('/reset/', resetPassword)

export default authenticationRouter