import express, { Express, Request, Response } from 'express'
import bodyParser from 'body-parser'
import helmet from 'helmet'
import morgan from 'morgan'
import databaseService from './services/database.service';
import authenticationRouter from './routes/authentication.route'
import { API_PORT } from './configs/'

const app: Express = express()

app.use(helmet());
app.use(bodyParser.json());
app.use(morgan('combined'))

databaseService.sequelize
    .sync()
    .then(() => {
        console.log("Synced db.");
    }).catch((err) => {
        console.log("Failed to sync db: " + err.message);
    });

app.use('/api/', authenticationRouter)

app.get('/', (req: Request, res: Response) => {
    res.send("Hello world!")  
})

app.listen(
    API_PORT,
    () => {
        console.log(`[server] : Server is running on http://localhost:${API_PORT}`)
    }
)