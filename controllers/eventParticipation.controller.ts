import { Request, Response } from "express";
import databaseService from "../services/database.service";

const Event = databaseService.event
const User = databaseService.user
const EventParticipation = databaseService.eventParticipation

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
    const {
        eventId,
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
    getEventCode,
    verifyParticipation
}