
const databaseConfig = {
    HOST: '',
    USER: '',
    PASSWORD: '',
    DB: '',
    dialect: 'sqlite',
    pool: {
        max: 5,
        min: 0,
        acquire: 30000,
        idle: 10000
    }
}

export default databaseConfig