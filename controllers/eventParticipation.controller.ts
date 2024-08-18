import { Request, Response } from "express";
import databaseService from "../services/database.service";

const Event = databaseService.event
const User = databaseService.user
const EventParticipation = databaseService.eventParticipation

const getEventParticipations = async (
    req: Request,
    res: Response,
) => {
    const userId = req.user.id
    var eventParticipations = await EventParticipation.findAll({
        where: { 
            participatedByUserId: userId
        },
        include: [{
            model: Event,
        }]
    })
    
    return res.status(200).json({
        status: "Success",
        data: eventParticipations,
        message: "Found events participated by the user"
    })
}

const createEventParticipation = async (
    req: Request,
    res: Response,
) => {
    const eventId = req.params.eventId

    const event = await Event.findByPk(eventId)
    if (!event) {
        return res.status(404).json({
            status: "Success",
            message: `Event with ID ${eventId} not found!`
        })
    }

    const user = req.user
    const eventParticipation = await EventParticipation.create({
        participatedByUserId: user.id,
        eventId: eventId,
    })

    return res.status(201).json({
        status: "Success",
        data: eventParticipation,
        message: `User ${user.id} participated in event ${eventId}`
    })
}

const deleteEventParticipation = async (
    req: Request,
    res: Response,
) => {
    const eventParticipationId = req.params.eventParticipationId

    await EventParticipation.destroy({ where: { eventParticipationId } })

    return res.status(200).json({
        status: "Success",
        message: `User successfully unpartipated from ${eventParticipationId}`
    })
}

const getEventCode = async (
    req: Request,
    res: Response
) => {
    const eventId = req.params.id

    const event = await Event.findByPk(eventId)
    if (!event) {
        res.status(404).json({
            status: 'Failed',
            data: [],
            message: "Event not found"
        })
    }

    return res.status(200).json({
        status: 'Status',
        data: event?.dataValues.code,
        message: "Event code here"
    })
}

const verifyParticipation = async (
    req: Request,
    res: Response
) => {
    const eventId = req.params.eventId
    const {
        eventCode,
        userId,
    } = req.body

    const event = await Event.findByPk(eventId)
    if (!event) {
        res.status(404).json({
            status: 'Failed',
            data: [],
            message: "Event not found"
        })
    }

    const user = await User.findByPk(userId)
    if (!user) {
        res.status(404).json({
            status: 'Failed',
            data: [],
            message: "User not found"
        })
    }

    if (!event?.dataValues.code == eventCode) {
        res.status(400).json({
            status: 'Failed',
            message: 'Code provided does not match event code'
        })
    }

    const eventParticipation = EventParticipation.create({
        userId: user?.dataValues.id,
        eventId: event?.dataValues.id
    })

    return res.status(201).json({
        status: 'Success',
        message: 'Code provided is correct and user has been marked as participating.',
        data: (await eventParticipation).dataValues,
    })
}

export {
    getEventParticipations,
    createEventParticipation,
    deleteEventParticipation,
    getEventCode,
    verifyParticipation
}