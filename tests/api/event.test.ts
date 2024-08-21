import request from 'supertest'
import expressApp from "../../espressApp";
import databaseService from '../../services/database.service';

const userPayload = {
    email: 'test@email.com',
    password: 'testPassword',
}

beforeAll(async () => {
    await databaseService.sequelize.sync()
    await databaseService.user.truncate()

    await request(expressApp)
        .post('/api/user/register/')
        .send(userPayload)
})

describe('POST /event/', () => {
    const payload = {
        name: "UCCD1234 Test Event",
        description: "This is a test event",
        startDate: "2024-08-14 16:00:00.000",
        endDate: "2024-08-14 18:00:00.000"
    }

    it("Should not be able to create event if not authorized", async () => {
        const response = await request(expressApp)
            .post('/api/event/')
            .send(payload)

        expect(response.status).toBe(401)
    })

    it("Should be able to create event after login.", async () => {
        const loginResponse = await request(expressApp)
            .post('/api/user/login/')
            .send(userPayload)

        expect(loginResponse.status).toBe(200)

        const authCookie = loginResponse.header['set-cookie']
        expect(authCookie[0]).not.toBeNull()

        const response = await request(expressApp)
            .post('/api/event/')
            .set('Cookie', authCookie)
            .send(payload)

        expect(response.status).toBe(201)
    })
})

describe('GET /event/:eventId', () => {
    it("", async () => {
        
    })
})

describe('PUT /event/:eventId', () => {
    it("", async () => {

    })
})

describe('DELETE /event/:eventId', () => {
    it("", async () => {

    })
})

describe('GET /event/:eventId/code', () => {
    it("", async () => {

    })
})

describe('POST /event/:eventId/join', () => {
    it("", async () => {

    })
})

describe('POST /event/:eventId/leave', () => {
    it("", async () => {

    })
})

describe('POST /event/:eventId/attend', () => {
    it("", async () => {

    })
})