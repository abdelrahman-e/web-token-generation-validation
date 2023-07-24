import {fireEvent, render, screen} from "@testing-library/react";
import TokenPage from "@/app/TokenPage";

// Mock the fetch function
global.fetch = jest.fn();

describe('TokenPage', () => {
    beforeEach(() => {
        // Clear all instances of the fetch mock
        jest.clearAllMocks();
    });

    it('renders the page title', () => {
        render(<TokenPage/>);
        expect(screen.getByText('Token Generation and Validation')).toBeInTheDocument();
    });

    it('allows the user to enter available digits', () => {
        render(<TokenPage/>);
        const input = screen.getByLabelText('Available Digits:') as HTMLInputElement;
        fireEvent.change(input, {target: {value: '2,4,7,9,0'}});
        expect(input.value).toBe('2,4,7,9,0');
    });

    it('generates a token when the user clicks the Generate Token button', async () => {
        // Mock the response from the token generation API
        (fetch as jest.Mock).mockImplementationOnce(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve({token: '2249-4472-0279-9420'}),
            })
        );

        render(<TokenPage/>);
        const input = screen.getByLabelText('Available Digits:');
        fireEvent.change(input, {target: {value: '2,4,7,9,0'}});
        const button = screen.getByText('Generate Token');
        fireEvent.click(button);

        // Wait for the generated token to be displayed
        const generatedToken = await screen.findByText('Generated Token:');
        expect(generatedToken).toBeInTheDocument();
    });

});
