import { tokenName } from "./config";

export async function fetchWithOpts(url, options) {
  const token = localStorage.getItem(tokenName);
  options.headers.Authorization = "Bearer " + token;
  const response = await fetch(url, options);
  if (!response.ok) {
    throw new Error("Request has failed");
  }
  const data = await response.json();
  return data;
}
