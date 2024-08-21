import request from 'supertest'
import expressApp from "../../espressApp";
import databaseService from '../../services/database.service';

const userPayload = {
    email: 'test@email.com',
    password: 'testPassword',
}

beforeAll(async () => {
    await databaseService.sequelize.sync({ force: true })
    
    await request(expressApp)
        .post('/api/user/register/')
        .send(userPayload)
})

var authCookie = ''
const eventPayload = {
    name: "UCCD1234 Test Event",
    description: "This is a test event",
    startDate: "2024-08-14 16:00:00.000",
    endDate: "2024-08-14 18:00:00.000"
}

describe('POST /api/event/', () => {
    it("Should not be able to create event if not authorized", async () => {
        const response = await request(expressApp)
            .post('/api/event/')
            .send(eventPayload)

        expect(response.status).toBe(401)
    })

    it("Should be able to create event after login.", async () => {
        const loginResponse = await request(expressApp)
            .post('/api/user/login/')
            .send(userPayload)

        expect(loginResponse.status).toBe(200)

        authCookie = loginResponse.header['set-cookie']
        expect(authCookie[0]).not.toBeNull()

        const response = await request(expressApp)
            .post('/api/event/')
            .set('Cookie', authCookie)
            .send(eventPayload)

        expect(response.status).toBe(201)
    })
})

describe('GET /api/event/:eventId', () => {
    it("Should not be able to get event when eventId is wrong.", async () => {
        const response = await request(expressApp)
            .get('/api/event/2')
            .set('Cookie', authCookie)

        expect(response.status).toBe(404)
    })

    it("Should be able to get event based on eventId.", async () => {
        const response = await request(expressApp)
            .get(`/api/event/1`)
            .set('Cookie', authCookie)

        expect(response.status).toBe(200)
        expect(response.body).not.toBeNull()
    })
})

describe('PUT /api/event/:eventId', () => {
    it("Should not be able to update event that does not exist.", async () => {
        const payload = eventPayload
        payload.description = "Updated"

        const updateResponse = await request(expressApp)
            .put('/api/event/2')
            .set('Cookie', authCookie)
            .send(payload)

        expect(updateResponse.status).toBe(400)
    })

    it("Should be able to update event.", async () => {
        const payload = eventPayload
        payload.description = "Updated"

        const updateResponse = await request(expressApp)
            .put('/api/event/1')
            .set('Cookie', authCookie)
            .send(payload)

        expect(updateResponse.status).toBe(201)
    })
})

describe('DELETE /api/event/:eventId', () => {
    it("Should not be able to delete the event", async () => {
        const deleteResponse = await request(expressApp)
            .delete('/api/event/2')
            .set('Cookie', authCookie)
            .send()
        
        expect(deleteResponse.status).toBe(400)
    })

    it("Should be able to delete the event", async () => {
        const deleteResponse = await request(expressApp)
            .delete('/api/event/1')
            .set('Cookie', authCookie)
            .send()
        
        expect(deleteResponse.status).toBe(200)

        const readResponse = await request(expressApp)
            .get('/api/event/1')
            .set('Cookie', authCookie)
            .send()

        expect(readResponse.status).toBe(404)
    })
})

describe('GET /api/event/:eventId/code', () => {
    it("Should not be able to get event code if event does not exist.", async () => {
        const response = await request(expressApp)
            .get('/api/event/20/code')
            .set('Cookie', authCookie)
            .send()
        
        expect(response.status).toBe(404)
    })

    it("Should be able to get event code.", async () => {
        const eventCreationResponse = await request(expressApp)
            .post('/api/event/')
            .set('Cookie', authCookie)
            .send(eventPayload)

        expect(eventCreationResponse.status).toBe(201)
        
        const response = await request(expressApp)
            .get(`/api/event/${eventCreationResponse.body.data.id}/code`)
            .set('Cookie', authCookie)
            .send()
        
        expect(response.status).toBe(200)
        expect(response.body.data).not.toBeNull()
    })
})

describe('POST /api/event/:eventId/join', () => {
    it("Should fail if event not found.", async () => {
        const response = await request(expressApp)
            .get('/api/event/200/join')
            .set('Cookie', authCookie)
            .send()

        expect(response.status).toBe(404)
    })

    it("Should be able to join an event.", async () => {
        const eventCreationResponse = await request(expressApp)
            .post('/api/event/')
            .set('Cookie', authCookie)
            .send(eventPayload)

        expect(eventCreationResponse.status).toBe(201)

        const response = await request(expressApp)
            .get(`/api/event/${eventCreationResponse.body.data.id}/join`)
            .set('Cookie', authCookie)
            .send()

        expect(response.status).toBe(201)
    })
})

describe('POST /api/event/:eventId/leave', () => {
    it("Should be able to leave event.", async () => {
        const eventCreationResponse = await request(expressApp)
            .post('/api/event/')
            .set('Cookie', authCookie)
            .send(eventPayload)

        expect(eventCreationResponse.status).toBe(201)

        const response = await request(expressApp)
            .get(`/api/event/${eventCreationResponse.body.data.id}/leave`)
            .set('Cookie', authCookie)
            .send()

        expect(response.status).toBe(200)
    })
})

describe('POST /api/event/:eventId/attend', () => {
    it("Should not be able to attend if event does not exists.", async () => {
        const response = await request(expressApp)
            .post('/api/event/2001/attend')
            .set('Cookie', authCookie)
            .send()

        expect(response.status).toBe(404)
    })
    
    it("Should not be able to attend if code provided does not match.", async () => {
        const eventCreationResponse = await request(expressApp)
            .post('/api/event/')
            .set('Cookie', authCookie)
            .send(eventPayload)

        expect(eventCreationResponse.status).toBe(201)
        
        const response = await request(expressApp)
            .put(`/api/event/${eventCreationResponse.body.data.id}/attend`)
            .set('Cookie', authCookie)
            .send({
                eventCode: 'randomInvalidCode'
            })

        expect(response.status).toBe(400)
    })

    it("Should be able to attend if event exists, and code provided matches.", async () => {
        const eventCreationResponse = await request(expressApp)
            .post('/api/event/')
            .set('Cookie', authCookie)
            .send(eventPayload)

        expect(eventCreationResponse.status).toBe(201)
        
        const response = await request(expressApp)
            .put(`/api/event/${eventCreationResponse.body.data.id}/attend`)
            .set('Cookie', authCookie)
            .send({
                eventCode: eventCreationResponse.body.data.code
            })

        expect(response.status).toBe(201)
    })
})