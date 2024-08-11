import express from 'express'
import {
    register,
    login,
    logout,
    changePassword
} from '../controllers/user.controller'

const userRouter = express.Router()
userRouter.post('/user/register/', register)
userRouter.post('/user/login/', login)
userRouter.post('/user/logout/', logout)
userRouter.post('/user/change_password/', changePassword)

export default userRouter