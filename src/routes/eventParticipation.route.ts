import express from 'express'
import { getEventCode, verifyParticipation } from '../controllers/eventParticipation.controller'
import { check, param } from 'express-validator'

const eventParticipationRouter = express.Router()

eventParticipationRouter.get(
    '/eventParticipation/:eventId',
    param('eventId').notEmpty().isInt(),
    getEventCode,
)

eventParticipationRouter.post(
    '/eventParticipation/',
    check('eventId').notEmpty().isInt(),
    check('userId').notEmpty().isInt(),
    verifyParticipation,
)

export default eventParticipationRouter