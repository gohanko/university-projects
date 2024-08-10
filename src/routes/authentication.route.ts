import express from 'express'
import {
    register,
    login,
    logout,
    resetPassword
} from '../controllers/authentication.controller'

const authenticationRouter = express.Router()
authenticationRouter.post('/register/', register)
authenticationRouter.post('/login/', login)
authenticationRouter.post('/logout/', logout)
authenticationRouter.post('/reset/', resetPassword)

export default authenticationRouter