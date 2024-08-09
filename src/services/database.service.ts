import databaseConfig from "../configs/datababse.config";
import { Sequelize } from "sequelize";

const sequelize = new Sequelize(
    databaseConfig.DB,
    databaseConfig.USER,
    databaseConfig.PASSWORD, 
    {
        host: databaseConfig.HOST,
        dialect: databaseConfig.dialect,
        operatorAliases: false,
        pool: {
            max: databaseConfig.pool.max,
            min: databaseConfig.pool.min,
            acquire: databaseConfig.pool.acquire,
            idle: databaseConfig.pool.idle
        }
    }
)

const databaseInstance = {
    Sequelize: Sequelize,
    sequelize: sequelize,
    user: require("../models/user.model.js")(sequelize)
}

export default databaseInstance