import express from 'express'
import { createEvent, deleteEvent, getEventDetail, getEvents, updateEvent } from '../controllers/event.controller'
import inputValidation from '../middlewares/inputValidation.middleware'
import { check, param } from 'express-validator'
import verifyToken from '../middlewares/verifyToken.middleware'

const eventRouter = express.Router()

eventRouter.get(
    '/events/',
    inputValidation,
    verifyToken,
    getEvents
)

eventRouter.get(
    '/events/:id',
    param('id').notEmpty().isInt(),
    inputValidation,
    verifyToken,
    getEventDetail
)

eventRouter.post(
    '/events/',
    check('name').notEmpty().isString(),
    check('description').notEmpty().isString(),
    check('startDate').notEmpty().isISO8601().toDate(),
    check('endDate').notEmpty().isISO8601().toDate(),
    check('parentEventId').isInt().optional({ nullable: true }),
    inputValidation,
    verifyToken,
    createEvent
)

eventRouter.put(
    '/events/:id',
    param('id').notEmpty().isInt(),
    check('name').notEmpty().isString(),
    check('description').notEmpty().isString(),
    check('startDate').notEmpty().isDate(),
    check('endDate').notEmpty().isDate(),
    check('parentEventId').isInt().optional({ nullable: true }),
    inputValidation,
    verifyToken,
    updateEvent
)

eventRouter.delete(
    '/events/:id',
    param('id').notEmpty().isInt(),
    inputValidation,
    verifyToken,
    deleteEvent
)

export default eventRouter