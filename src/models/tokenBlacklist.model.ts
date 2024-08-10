import { DataTypes, Sequelize } from "sequelize";

const tokenBlacklist = (sequelize: Sequelize) => {
    const TokenBlacklist = sequelize.define(
        'tokenBlacklist',
        {
            id: {
                type: DataTypes.INTEGER,
                primaryKey: true,
                autoIncrement: true,
            },
            token: {
                type: DataTypes.STRING
            }
        }
    )

    return TokenBlacklist
}

export default tokenBlacklist