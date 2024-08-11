import express, { Express, Request, Response } from 'express'
import bodyParser from 'body-parser'
import helmet from 'helmet'
import databaseService from './services/database.service';
import userRouter from './routes/user.route'
import { API_PORT } from './configs'
import eventRouter from './routes/event.route';
import eventParticipationRouter from './routes/eventParticipation.route';

const expressApp: Express = express()

expressApp.use(helmet());
expressApp.use(bodyParser.json());

expressApp.use('/api/', userRouter)
expressApp.use('/api/', eventRouter)
expressApp.use('/api/', eventParticipationRouter)

export default expressApp