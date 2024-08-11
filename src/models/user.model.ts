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
            firstName: {
                type: DataTypes.STRING,
            },
            lastName: {
                type: DataTypes.STRING,
            },
            birthday: {
                type: DataTypes.DATE,
            },
        },
    )

    return User
}

export default user