import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import React from 'react';
import TokenPage from '../app/TokenPage';
import { generateTokenApi } from '../api/GenerationApi';
import { validateApi } from '../api/ValidationApi';

jest.mock('../api/GenerationApi');
jest.mock('../api/ValidationApi');

describe('TokenPage', () => {
  afterAll(() => {
    jest.clearAllMocks();
    jest.resetAllMocks();
  });

  beforeEach(() => {
    render(<TokenPage />);
  });
  it('should generate and validate token', async () => {
    (generateTokenApi as jest.Mock).mockResolvedValueOnce('1234');
    (validateApi as jest.Mock).mockResolvedValueOnce('true');

    const generateButton = screen.getByTestId('generateButton');
    await userEvent.click(generateButton);
    const generatedTokenField = await screen.getByTestId('generatedToken');

    expect(generatedTokenField).toBeTruthy();

    const validateButton = screen.getByText('Validate Token');
    await userEvent.click(validateButton);

    const validTokenLabel = await screen.findByText('Valid Token');
    expect(validTokenLabel).toBeTruthy();
  });

  it('should have invalid token incase validate is false', async () => {
    (generateTokenApi as jest.Mock).mockResolvedValueOnce('1234');
    (validateApi as jest.Mock).mockResolvedValueOnce('false');

    const generateButton = screen.getByTestId('generateButton');
    await userEvent.click(generateButton);

    const validateButton = screen.getByText('Validate Token');
    await userEvent.click(validateButton);

    const tokenLabel = await screen.findByText('Invalid generated Token');
    expect(tokenLabel).toBeTruthy();
  });
});

