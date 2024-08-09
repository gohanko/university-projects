import dotenv from 'dotenv'
import express, { Express, Request, Response } from 'express'
import bodyParser from 'body-parser'
import cors from 'cors';
import helmet from 'helmet'
import morgan from 'morgan'
import authenticationRouter from './routes/authentication.route'

dotenv.config()

const port = process.env.PORT
const app: Express = express()

app.use(helmet());
app.use(bodyParser.json());
app.use(cors);
app.use(morgan('combined'))

app.use('/auth/', authenticationRouter)

app.get('/', (req: Request, res: Response) => {
    res.send("Hello world!")  
})

app.listen(
    port,
    () => {
        console.log(`[server] : Server is running on http://localhost:${port}`)
    }
)