import { Sequelize } from "sequelize";
import user from '../models/user.model'
import tokenBlacklist from "../models/tokenBlacklist.model";

const sequelize = new Sequelize(
    {
        dialect: 'sqlite',
        storage: 'database.sqlite3'
    }
)

const databaseService = {
    Sequelize: Sequelize,
    sequelize: sequelize,
    user: user(sequelize),
    tokenBlacklist: tokenBlacklist(sequelize)
}

export default databaseService