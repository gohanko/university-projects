import dotenv from 'dotenv'

dotenv.config()

const SECRET_ACCESS_TOKEN = process.env.SECRET_ACCESS_TOKEN
const API_PORT = process.env.PORT

export {
    SECRET_ACCESS_TOKEN,
    API_PORT
}