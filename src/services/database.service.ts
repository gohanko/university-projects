import { Sequelize } from "sequelize";
import user from '../models/user.model'
import tokenBlacklist from "../models/tokenBlacklist.model";
import event from '../models/event.model'

const sequelize = new Sequelize(
    {
        dialect: 'sqlite',
        storage: 'database.sqlite3',
        logging: false
    }
)

const databaseService = {
    Sequelize: Sequelize,
    sequelize: sequelize,
    user: user(sequelize),
    tokenBlacklist: tokenBlacklist(sequelize),
    event: event(sequelize)
}

export default databaseService