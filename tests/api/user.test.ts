import request from 'supertest'
import expressApp from "../../espressApp";
import databaseService from '../../services/database.service';

beforeAll(() => {
    databaseService.user.truncate()
})

afterAll(() => {
    databaseService.user.truncate()
})

describe('POST /api/user/register/', () => {
    it('Should not register when email set to nothing.', async () => {
        const payload = {
            email: '',
            password: 'testPassword',
        }

        const response = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(response.status).toBe(400)
    })

    it('Should not register when password set to nothing.', async () => {
        const payload = {
            email: 'example@example.com',
            password: '',
        }

        const response = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(response.status).toBe(400)
    })

    it('Should not register when email is in the wrong format.', async () => {
        const payload = {
            email: 'example',
            password: 'testPassword',
        }

        const response = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(response.status).toBe(400)
    })

    it('Should register when both field are properly set.', async () => {
        const payload = {
            email: 'example@example.com',
            password: 'testPassword',
        }

        const response = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(response.status).toBe(201)
    })

    it("Should not register when email is already used.", async () => {
        const payload = {
            email: 'example@example.com',
            password: 'testPassword',
        }
        
        const registerWithSameEmailResponse = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(registerWithSameEmailResponse.status).toBe(400)
    })
})

var authCookie = ''

describe('POST /api/user/login/', () => {
    it('Should not login without email', async () => {            
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
        expect(response.body.accessToken).not.toBeNull()
        expect(response.header['set-cookie'][0]).not.toBeNull()
        authCookie = response.header['set-cookie']
    })
})

describe('POST /api/user/logout/', () => {
    it('Should logout and return 200', async () => {
        const response = await request(expressApp)
            .post('/api/user/logout/')
            .set('Cookie', authCookie)
        
        expect(response.status).toBe(200)
    })

    it('Should return 204 when user try to logout with same token', async () => {
        const response = await request(expressApp)
            .post('/api/user/logout/')
            .set('Cookie', authCookie)
        
        expect(response.status).toBe(204)
    })
})

describe('POST /api/user/change_password/', () => {
    it('', async () => {
        
    })
})