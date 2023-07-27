import { VALIDATION_API_URI } from '@/constants/TokenConstants';

export const validateApi = async (generatedToken: string): Promise<string> => {
  let response = await fetch(VALIDATION_API_URI + `${generatedToken}`);
  response = await response.json();
  const responseString = response as unknown as string;
  return responseString === 'true' ? responseString : 'false';
};
