import request from 'supertest'
import expressApp from "../../src/espressApp";

describe('POST /api/user/register/', () => {
    it('Should not be able to register when payload is not properly set.', async () => {
        const payload = {
            email: '',
            password: '',
        }

        const response = await request(expressApp)
            .post('/api/user/register/')
            .send(payload)

        expect(response).toBe(400)
    })
})

describe('POST /api/user/login/', () => {
    it('', async () => {
        
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