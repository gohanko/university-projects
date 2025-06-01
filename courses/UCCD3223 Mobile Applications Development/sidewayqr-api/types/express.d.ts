declare namespace Express {
    interface Request {
        user: {
            id: number,
            name: string,
            description: string,
            startDate: string,
            endDate: string,
            code: string
        }
    }
}
