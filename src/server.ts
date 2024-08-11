import express, { Express, Request, Response } from 'express'
import bodyParser from 'body-parser'
import helmet from 'helmet'
import databaseService from './services/database.service';
import authenticationRouter from './routes/authentication.route'
import { API_PORT } from './configs/'
import eventRouter from './routes/event.route';

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

app.use('/api/', authenticationRouter)
app.use('/api/', eventRouter)

app.get('/', (req: Request, res: Response) => {
    res.send("Hello world!")  
})

app.listen(
    API_PORT,
    () => {
        console.log(`[server] : Server is running on http://localhost:${API_PORT}`)
    }
)