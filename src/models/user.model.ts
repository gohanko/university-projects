import { Sequelize, DataTypes } from "sequelize"

const user = (sequelize: Sequelize) => {
    const User = sequelize.define(
        "user",
        {
            id: {
                type: DataTypes.INTEGER,
                primaryKey: true,
                autoIncrement: true,
            },
            email: {
                type: DataTypes.STRING
            },
            password: {
                type: DataTypes.STRING,
            },
        },
    )

    return User
}

export default user