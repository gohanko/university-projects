import express from 'express'
import {
    createEvent,
    readEvent,
    updateEvent,
    deleteEvent,
    getAllEvents,
    getEventCode,
    joinEvent,
    leaveEvent,
    attendEvent,
} from '../controllers/event.controller'
import inputValidation from '../middlewares/inputValidation.middleware'
import { check, param } from 'express-validator'
import {
    mustBeAuthorized 
} from '../middlewares/mustBeAuthorized.middleware'

const eventRouter = express.Router()

eventRouter.post(
    '/event/',
    check('name').notEmpty().isString(),
    check('description').notEmpty().isString(),
    check('startDate').notEmpty().isISO8601().toDate(),
    check('endDate').notEmpty().isISO8601().toDate(),
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
    check('name').notEmpty().isString(),
    check('description').notEmpty().isString(),
    check('startDate').notEmpty().isISO8601().toDate(),
    check('endDate').notEmpty().isISO8601().toDate(),
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
    '/event/:eventId/code',
    param('eventId').notEmpty().isInt(),
    inputValidation,
    mustBeAuthorized,
    getEventCode
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
    check('eventCode').notEmpty(),
    inputValidation,
    mustBeAuthorized,
    attendEvent
)

// TODO later
eventRouter.get(
    '/event/all/',
    inputValidation,
    mustBeAuthorized,
    getAllEvents
)

export default eventRouter