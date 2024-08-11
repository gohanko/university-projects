import express from 'express'
import {
    register,
    login,
    logout,
    resetPassword
} from '../controllers/authentication.controller'

const authenticationRouter = express.Router()
authenticationRouter.post('/auth/register/', register)
authenticationRouter.post('/auth/login/', login)
authenticationRouter.post('/auth/logout/', logout)
authenticationRouter.post('/auth/reset/', resetPassword)

export default authenticationRouter