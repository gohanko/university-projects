import express, { Express, Request, Response } from 'express'
import bodyParser from 'body-parser'
import helmet from 'helmet'
import databaseService from './services/database.service';
import userRouter from './routes/user.route'
import { API_PORT } from './configs/'
import eventRouter from './routes/event.route';
import eventParticipationRouter from './routes/eventParticipation.route';

const app: Express = express()

app.use(helmet());
app.use(bodyParser.json());

databaseService.sequelize
    .sync()
    .then(() => {
        console.log("[server] : Synced database");
    }).catch((err) => {
        console.log("[server] : Failed to sync database: " + err.message);
    });

app.use('/api/', userRouter)
app.use('/api/', eventRouter)
app.use('/api/', eventParticipationRouter)

app.listen(
    API_PORT,
    () => console.log(`[server] : Server is running on http://localhost:${API_PORT}`)
)
