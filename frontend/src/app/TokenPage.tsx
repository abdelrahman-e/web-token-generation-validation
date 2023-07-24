'use client'

import React, {useState} from 'react';
import {generateTokenApi} from "@/api/GenerationApi";
import {Alert, Box, Button, TextField} from "@mui/material";
import {validateApi} from "@/api/ValidationApi";

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
            setGeneratedTokenError('')
            setValidationResult('');
        } catch (err) {
            console.log(err);
            setGeneratedTokenError(err as string);
            setGeneratedToken('');
            setValidationResult('');
        }

    };

    const handleValidateToken = async (): Promise<void> => {
        try {
            const result = await validateApi(generatedToken);
            setValidationResult(result);
        } catch (err) {
            console.log(err);
        }
    };

    const getGeneratedFieldColor = () => {
        return validationResult === 'true' ? 'success' : 'primary';
    }

    const getGeneratedFieldLabel = (): string => {
        if (validationResult === 'false') {
            return 'Invalid generated Token';
        } else if (validationResult === 'true') {
            return 'Valid Token';
        } else {
            return 'Generated Token';
        }
    }

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
            <>{generatedTokenError && (<Alert severity="error">{generatedTokenError.toString()}</Alert>)}</>
            <Box display="flex">
                {generatedToken && (
                    <><TextField variant="standard"
                                 label={getGeneratedFieldLabel()}
                                 value={generatedToken}
                                 InputProps={{
                                     readOnly: true,
                                 }}
                                 error={validationResult === 'false'}
                                 color={getGeneratedFieldColor()}
                                 focused
                    >
                    </TextField><Button onClick={handleValidateToken}>Validate Token</Button></>
                )}
            </Box>
        </Box>
    );
}
