import { Request, Response } from "express"
import databaseService from "../services/database.service"

const Event = databaseService.event

const getEvents = async (
    req: Request,
    res: Response
) => {
    const events = await Event.findAll()

    return res.status(200).json({
        status: "Success",
        data: events
    })
}

const getEventDetail = async (
    req: Request,
    res: Response
) => {
    const eventId = req.params.id

    const event = await Event.findByPk(eventId)
    return res.status(200).json({
        status: "Success",
        data: event?.dataValues
    })
}

const createEvent = async (
    req: Request,
    res: Response
) => {
    const {
        name,
        description,
        startDate,
        endDate,
        parentEventId,
    } = req.body;

    const newEvent = await Event.create({
        name,
        description,
        startDate,
        endDate,
        parentEventId
    })

    return res.status(201).json({
        status: "Success",
        message: "New event have been created!",
        data: newEvent.dataValues
    })
}

const updateEvent = async (
    req: Request,
    res: Response
) => {
    const eventId = req.params.id;
    const eventToUpdate = await Event.findByPk(eventId)

    if (!eventToUpdate) {
        return res.status(400).json({
            status: "Failed",
            message: `Event with event ID ${eventId} not found`,
            data: [],
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
        status: "Success",
        message: "New event have been created!",
        data: updatedEvent.dataValues
    })
}

const deleteEvent = async (
    req: Request,
    res: Response
) => {
    const eventId = req.params.id;
    const eventToDelete = await Event.findByPk(eventId)

    if (!eventToDelete) {
        return res.status(400).json({
            status: "Failed",
            message: `Event with ID ${eventId} not found`,
            data: [],
        })
    }

    await eventToDelete.destroy()

    return res.status(200).json({
        status: "Success",
        message: `Event with ID ${eventId} deleted`,
        data: []
    })
}

export {
    getEvents,
    getEventDetail,
    createEvent,
    updateEvent,
    deleteEvent,
}