import fs from 'fs'
import databaseService from '../services/database.service'

const User = databaseService.user
const Event = databaseService.event
const EventParticipation = databaseService.eventParticipation

const loadJSON = (path: string) => {
    const data = fs.readFileSync(path, { encoding: 'utf8', flag: 'r' })
    return JSON.parse(data)
}

const loadFixtures = async () => {
    const userList = loadJSON('./fixtures/user.json')

    userList.map(async (user: any) => {
        await User.create({
            email: user.email,
            password: user.password
        })
    })

    const eventList = loadJSON('./fixtures/event.json')

    eventList.map(async (event: any) => {
        await Event.create({
            name: event.name,
            description: event.description,
            startDate: event.startDate,
            endDate: event.endDate,
            createdBy: event.createdBy
        })
    })

    const eventParticipationList = loadJSON('./fixtures/eventParticipation.json')

    eventParticipationList.map(async (eventParticipation: any) => {
        await EventParticipation.create({
            userId: eventParticipation.userId,
            eventId: eventParticipation.eventId
        })
    })
}

export {
    loadFixtures
}