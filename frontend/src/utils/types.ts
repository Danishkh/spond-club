export interface MemberType {
  id: string
  name: string
}

export interface Group {
  formId: string
  clubId: string
  title: string
  description: string
  registrationOpens: string
  memberTypes: MemberType[]
}

export interface FormData {
  memberTypeId: string
  firstName: string
  lastName: string
  email: string
  phone: string
  birthDate: string
}

export interface RegistrationRequest {
  memberTypeId: string
  firstName: string
  lastName: string
  email: string
  phone: string
  birthDate: string
}

export interface RegistrationResponse {
  id: string
  message: string
}