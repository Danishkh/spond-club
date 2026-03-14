import axios from 'axios'
import { Group, RegistrationRequest, RegistrationResponse } from '../utils/types'

const BASE_URL = '/api'

export async function getGroup(formId: string): Promise<Group> {
  const { data } = await axios.get<Group>(`${BASE_URL}/forms/${formId}`)
  return data
}

export async function submitRegistration(formId: string, registrationData: RegistrationRequest): Promise<RegistrationResponse> {
  const { data } = await axios.post<RegistrationResponse>(
    `${BASE_URL}/forms/${formId}/registrations`,
    registrationData,
    { headers: { 'Content-Type': 'application/json' } }
  )
  return data
}