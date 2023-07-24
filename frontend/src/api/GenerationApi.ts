import {GENERATION_API_URI} from "@/constants/TokenConstants";

export const generateTokenApi = async (availableDigits: string): Promise<string> => {
    try {
        const response = await fetch(GENERATION_API_URI + `${availableDigits}`, {
            method: 'POST',
        });

        const result = await response.text();
        if (!response.ok) {
            throw new Error(`An error occurred: ${response.status}` + result);
        }
        return result;
    } catch (err) {
        console.log(err);
        throw new Error(err as string);
    }

};