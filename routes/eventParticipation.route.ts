import express from 'express'
import {
    getEventParticipations,
    createEventParticipation,
    deleteEventParticipation,
    getEventCode,
    verifyParticipation
} from '../controllers/eventParticipation.controller'
import { check, param } from 'express-validator'
import { mustBeAuthorized } from '../middlewares/mustBeAuthorized.middleware'

const eventParticipationRouter = express.Router()

eventParticipationRouter.get(
    '/event/participation/',
    param('eventId').notEmpty().isInt(),
    mustBeAuthorized,
    getEventParticipations,
)

eventParticipationRouter.post(
    '/event/participation/:eventId/join',
    param('eventId').notEmpty().isInt(),
    mustBeAuthorized,
    createEventParticipation
)

eventParticipationRouter.delete(
    '/event/participation/:eventParticipationId',
    param('eventParticipationId').notEmpty().isInt(),
    mustBeAuthorized,
    deleteEventParticipation
)

eventParticipationRouter.get(
    '/event/participation/:eventId/getCode/',
    param('eventId').notEmpty().isInt(),
    mustBeAuthorized,
    getEventCode,
)

eventParticipationRouter.post(
    '/event/participation/:eventId/verify',
    param('eventId').notEmpty().isInt(),
    check('eventCode').notEmpty().isInt(),
    mustBeAuthorized,
    verifyParticipation,
)

export default eventParticipationRouter