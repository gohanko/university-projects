import { Request, Response } from "express"
import databaseService from "../services/database.service"

const User = databaseService.user
const Event = databaseService.event
const EventParticipation = databaseService.eventParticipation

export const createEvent = async (
    req: Request,
    res: Response
) => {
    const {
        name,
        description,
        startDate,
        endDate,
    } = req.body;
    const createdBy = req.user.id

    const newEvent = await Event.create({
        name,
        description,
        startDate,
        endDate,
        createdBy
    })

    return res.status(201).json({
        status: "success",
        message: "New event have been created!",
        data: newEvent.dataValues
    })
}


export const readEvent = async (
    req: Request,
    res: Response
) => {
    const eventId = req.params.eventId

    const event = await Event.findByPk(eventId)
    return res.status(200).json({
        status: "success",
        data: event?.dataValues
    })
}

export const updateEvent = async (
    req: Request,
    res: Response
) => {
    const eventId = req.params.eventId;
    const eventToUpdate = await Event.findByPk(eventId)

    if (!eventToUpdate) {
        return res.status(400).json({
            status: "failed",
            message: `Event with event ID ${eventId} not found`,
        })
    }

    const {
        name,
        description,
        startDate,
        endDate,
        parentEventId,
    } = req.body;

    const updatedEvent = await eventToUpdate.update({
        name,
        description,
        startDate,
        endDate,
        parentEventId
    })

    return res.status(201).json({
        status: "success",
        message: "New event have been created!",
        data: updatedEvent.dataValues
    })
}

export const deleteEvent = async (
    req: Request,
    res: Response
) => {
    const eventId = req.params.eventId;
    const eventToDelete = await Event.findByPk(eventId)

    if (!eventToDelete) {
        return res.status(400).json({
            status: "failed",
            message: `Event with ID ${eventId} not found`,
        })
    }

    await eventToDelete.destroy()

    return res.status(200).json({
        status: "success",
        message: `Event with ID ${eventId} deleted`,
    })
}

export const getEventCode = async (
    req: Request,
    res: Response
) => {
    const eventId = req.params.eventId

    const event = await Event.findByPk(eventId)
    if (!event) {
        res.status(404).json({
            status: 'Failed',
            message: "Event not found",
        })
    }

    return res.status(200).json({
        status: 'Status',
        message: "Event code here",
        data: event?.dataValues.code,
    })
}

export const joinEvent = async (
    req: Request,
    res: Response,
) => {
    const eventId = req.params.eventId

    const event = await Event.findByPk(eventId)
    if (!event) {
        return res.status(404).json({
            status: "failure",
            message: `Event with ID ${eventId} not found!`
        })
    }

    const user = req.user
    const eventParticipation = await EventParticipation.create({
        userId: user.id,
        eventId: eventId,
    })

    return res.status(201).json({
        status: "success",
        data: eventParticipation,
        message: `User ${user.id} participated in event ${eventId}`
    })
}

export const leaveEvent = async (
    req: Request,
    res: Response,
) => {
    const eventId = req.params.eventId
    const userId = req.params.userId

    await EventParticipation.destroy({
        where: {
            eventId: eventId,
            userId: userId
        }
    })

    return res.status(200).json({
        status: "success",
        message: `User successfully left event`
    })
}

export const attendEvent = async (
    req: Request,
    res: Response
) => {
    const eventId = req.params.eventId
    const { eventCode } = req.body

    const event = await Event.findByPk(eventId)
    if (!event) {
        return res.status(404).json({
            status: 'Failed',
            message: "Event not found."
        })
    }

    if (event?.dataValues.code !== eventCode) {
        return res.status(400).json({
            status: 'Failed',
            message: 'Code provided does not match event code.'
        })
    }

    const userId = req.user.id
    const eventParticipation = await EventParticipation.findOne({
        where: {
            userId: userId,
            eventId: eventId
        }
    })

    eventParticipation?.update({
        isAttended: true
    })

    return res.status(201).json({
        status: 'Success',
        message: 'Code provided is correct and user has been marked as participating.',
    })
}

// TODO
export const getAllEvents = async (
    req: Request,
    res: Response
) => {
    const events = await Event.findAll({ where: { createdByUserId: req.user.id }})

    return res.status(200).json({
        status: "success",
        data: events
    })
}