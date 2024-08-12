import express from 'express'
import { getEventCode, verifyParticipation } from '../controllers/eventParticipation.controller'
import { check, param } from 'express-validator'
import verifyToken from '../middlewares/verifyToken.middleware'

const eventParticipationRouter = express.Router()

eventParticipationRouter.get(
    '/eventParticipation/:eventId',
    param('eventId').notEmpty().isInt(),
    verifyToken,
    getEventCode,
)

eventParticipationRouter.post(
    '/eventParticipation/',
    check('eventId').notEmpty().isInt(),
    check('userId').notEmpty().isInt(),
    verifyToken,
    verifyParticipation,
)

export default eventParticipationRouter