import jwt from 'jsonwebtoken';
import { SECRET_ACCESS_TOKEN } from '../configs';

const generateAccessToken = (id: Number) => {
    const payload = {
        id
    }

    return jwt.sign(
        payload,
        SECRET_ACCESS_TOKEN as string
    )
}

export {
    generateAccessToken
}