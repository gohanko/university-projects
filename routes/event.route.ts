import express from 'express'
import {
    createEvent,
    readEvent,
    updateEvent,
    deleteEvent,
    getEventQRCode,
    joinEvent,
    leaveEvent,
    attendEvent,
    getEvents,
} from '../controllers/event.controller'
import inputValidation from '../middlewares/inputValidation.middleware'
import { body, check, param } from 'express-validator'
import {
    mustBeAuthorized 
} from '../middlewares/mustBeAuthorized.middleware'

const eventRouter = express.Router()

eventRouter.post(
    '/event/',
    body('name').notEmpty().isString(),
    body('description').notEmpty().isString(),
    body('startDate').notEmpty().isISO8601().toDate(),
    body('endDate').notEmpty().isISO8601().toDate(),
    inputValidation,
    mustBeAuthorized,
    createEvent
)

eventRouter.get(
    '/event/:eventId',
    param('eventId').notEmpty().isInt(),
    inputValidation,
    mustBeAuthorized,
    readEvent
)

eventRouter.put(
    '/event/:eventId',
    param('eventId').notEmpty().isInt(),
    body('name').notEmpty().isString(),
    body('description').notEmpty().isString(),
    body('startDate').notEmpty().isISO8601().toDate(),
    body('endDate').notEmpty().isISO8601().toDate(),
    inputValidation,
    mustBeAuthorized,
    updateEvent
)

eventRouter.delete(
    '/event/:eventId',
    param('eventId').notEmpty().isInt(),
    inputValidation,
    mustBeAuthorized,
    deleteEvent
)

eventRouter.get(
    '/event/:eventId/qrcode',
    param('eventId').notEmpty().isInt(),
    inputValidation,
    mustBeAuthorized,
    getEventQRCode
)

eventRouter.get(
    '/event/:eventId/join',
    param('eventId').notEmpty().isInt(),
    inputValidation,
    mustBeAuthorized,
    joinEvent
)

eventRouter.get(
    '/event/:eventId/leave',
    param('eventId').notEmpty().isInt(),
    inputValidation,
    mustBeAuthorized,
    leaveEvent
)

eventRouter.put(
    '/event/:eventId/attend',
    param('eventId').notEmpty().isInt(),
    body('eventCode').notEmpty(),
    inputValidation,
    mustBeAuthorized,
    attendEvent
)

eventRouter.get(
    '/events/',
    inputValidation,
    mustBeAuthorized,
    getEvents
)

export default eventRouter