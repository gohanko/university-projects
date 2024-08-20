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

eventRouter.use(inputValidation, mustBeAuthorized)

eventRouter.post(
    '/event/',
    check('name').notEmpty().isString(),
    check('description').notEmpty().isString(),
    check('startDate').notEmpty().isISO8601().toDate(),
    check('endDate').notEmpty().isISO8601().toDate(),
    check('parentEventId').isInt().optional({ nullable: true }),
    createEvent
)

eventRouter.get(
    '/event/:eventId',
    param('eventId').notEmpty().isInt(),
    readEvent
)

eventRouter.put(
    '/event/:eventId',
    param('eventId').notEmpty().isInt(),
    check('name').notEmpty().isString(),
    check('description').notEmpty().isString(),
    check('startDate').notEmpty().isDate(),
    check('endDate').notEmpty().isDate(),
    check('parentEventId').isInt().optional({ nullable: true }),
    updateEvent
)

eventRouter.delete(
    '/event/:eventId',
    param('eventId').notEmpty().isInt(),
    deleteEvent
)

eventRouter.get(
    '/event/:eventId/code',
    param('eventId').notEmpty().isInt(),
    getEventCode
)

eventRouter.get(
    '/event/:eventId/join',
    param('eventId').notEmpty().isInt(),
    joinEvent
)

eventRouter.get(
    '/event/:eventId/leave',
    param('eventId').notEmpty().isInt(),
    leaveEvent
)

eventRouter.get(
    '/event/:eventId/attend',
    param('eventId').notEmpty().isInt(),
    attendEvent
)

// TODO later
eventRouter.get(
    '/event/all/',
    getAllEvents
)

export default eventRouter