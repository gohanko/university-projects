import express from 'express'
import {
    register,
    login,
    logout,
    resetPassword
} from '../controllers/user.controller'

const userRouter = express.Router()
userRouter.post('/user/register/', register)
userRouter.post('/user/login/', login)
userRouter.post('/user/logout/', logout)
userRouter.post('/user/reset/', resetPassword)

export default userRouter