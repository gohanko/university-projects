import request from 'supertest'
import expressApp from "../../src/espressApp";
import databaseService from '../../src/services/database.service';

describe('POST /api/user/register/', () => {
    beforeEach(() => {
        databaseService.user.truncate()
    })

    it('Should not be able to register when email set to nothing.', async () => {
        const payload = {
            email: '',
            password: 'testPassword',
        }

        const response = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(response.status).toBe(400)
    })

    it('Should not be able to register when password set to nothing.', async () => {
        const payload = {
            email: 'example@example.com',
            password: '',
        }

        const response = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(response.status).toBe(400)
    })

    it('Should not be able to register when email is in the wrong format.', async () => {
        const payload = {
            email: 'example',
            password: 'testPassword',
        }

        const response = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(response.status).toBe(400)
    })

    it('Should be able to register when both field are properly set, and should not register when email is already used.', async () => {
        const payload = {
            email: 'example@example.com',
            password: 'testPassword',
        }

        const response = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(response.status).toBe(201)

        const registerWithSameEmailResponse = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(registerWithSameEmailResponse.status).toBe(400)
    })
})

describe('POST /api/user/login/', () => {

    it('Should not login without email', async () => {
        const registerPayload = {
            email: 'example@example.com',
            password: 'testPassword'
        }

        await request(expressApp)
            .post('/api/user/login/')
            .send(registerPayload)
            
        const payload = {
            email: '',
            password: 'testPassword',
        }

        const response = await request(expressApp)
            .post('/api/user/login/')
            .send(payload)

        expect(response.status).toBe(400)
    })

    it('Should not login without password', async () => {
        const payload = {
            email: 'example@example.com',
            password: '',
        }

        const response = await request(expressApp)
            .post('/api/user/login/')
            .send(payload)

        expect(response.status).toBe(400)
    })

    it('Should login and return accessToken', async () => {
        const payload = {
            email: 'example@example.com',
            password: 'testPassword',
        }

        const response = await request(expressApp)
            .post('/api/user/login/')
            .send(payload)

        expect(response.status).toBe(200)
        expect(response.header['set-cookie'][0]).not.toBeNull()
    })
})

describe('POST /api/user/logout/', () => {
    it('', async () => {
        
    })
})

describe('POST /api/user/change_password/', () => {
    it('', async () => {
        
    })
})