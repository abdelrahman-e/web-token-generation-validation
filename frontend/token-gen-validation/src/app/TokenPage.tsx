'use client'

import React, {useState} from 'react';
import {VALIDATION_API_URI} from "@/constants/TokenConstants";
import {generateTokenApi} from "@/api/GenerationApi";
import {Box, Button, TextField} from "@mui/material";

export default function TokenPage() {
    const [availableDigits, setAvailableDigits] = useState('');
    const [generatedToken, setGeneratedToken] = useState('');
    const [generatedTokenError, setGeneratedTokenError] = useState('');
    const [validationResult, setValidationResult] = useState('');

    const handleGenerateToken = async (): Promise<void> => {
        setGeneratedTokenError('');
        try {
            const result = await generateTokenApi(availableDigits);
            setGeneratedToken(result);
            setValidationResult('');
        } catch (err) {
            console.log(err);
            setGeneratedTokenError(err as string);
            setGeneratedToken('');

        }

    };

    const handleValidateToken = async (): Promise<void> => {
        let response = await fetch(VALIDATION_API_URI + `${generatedToken}`);
        response = await response.json();
        const result = response ? 'true' : 'false';
        setValidationResult(result);
        console.log(validationResult);
    };

    return (
        <Box>
            <h1>Token Generation and Validation </h1>
            <Box display="flex">
                {/*<Label>Available Digits:</Label>*/}
                <TextField

                    variant="standard"

                    label="Available Digits:"
                    type="text"
                    id="availableDigits"
                    value={availableDigits}
                    onChange={e => setAvailableDigits(e.target.value)}
                />
                <Button onClick={handleGenerateToken}>Generate Token</Button>
            </Box>
            <Box display="flex">
                {generatedToken && (
                    <><TextField variant="standard"
                                 label={validationResult === 'false' ? 'Invalid generated Token' : 'Generated Token'}
                                 value={generatedToken}
                                 InputProps={{
                                     readOnly: true,
                                 }}
                                 error={validationResult === 'false'}
                    >
                    </TextField><Button onClick={handleValidateToken}>Validate Token</Button></>
                )}
            </Box>
        </Box>
    );
}
