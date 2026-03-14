import { useState } from 'react'
import {FormData} from '../../utils/types.ts'
import { buttonStyles, inputStyles, labelStyles, errorStyles } from '../../utils/styles.ts'

interface StepTwoProps {
    formData: FormData
    update: (fields: Partial<FormData>) => void
    next: () => void
    back: () => void
}

interface FormErrors {
    firstName?: string
    lastName?: string
    email?: string
    phone?: string
    birthDate?: string
}

export default function StepTwo({ formData, update, next, back }: StepTwoProps) {
    const [errors, setErrors] = useState<FormErrors>({})

    const validate = (): FormErrors => {
        const newErrors: FormErrors = {}

        if (!formData.firstName.trim()) newErrors.firstName = 'First name is required'
        if (!formData.lastName.trim()) newErrors.lastName = 'Last name is required'
        if (!formData.email.trim()) newErrors.email = 'Email is required'
        else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) newErrors.email = 'Email is invalid'
        if (!formData.birthDate) newErrors.birthDate = 'Birth date is required'
        else if (new Date(formData.birthDate) >= new Date()) newErrors.birthDate = 'Birth date must be in the past'
        if (!formData.phone) newErrors.phone = 'Phone number is required'
        else if (formData.phone && !/^\+?[0-9\s\-]{7,20}$/.test(formData.phone)) newErrors.phone = 'Phone number is invalid'

        return newErrors
    }

    const handleNext = () => {
        const newErrors = validate()
        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors)
            return
        }
        setErrors({})
        next()
    }

    return (
        <div>
            <h2 className="text-xl font-bold text-gray-800 mb-6">Member information</h2>

            <div className="space-y-4">
                <div>
                    <label className={labelStyles}>Firstname</label>
                    <input
                        type="text"
                        value={formData.firstName}
                        onChange={e => update({ firstName: e.target.value })}
                        className={inputStyles}
                    />
                    {errors.firstName && <p className={errorStyles}>{errors.firstName}</p>}
                </div>

                <div>
                    <label className={labelStyles}>Lastname</label>
                    <input
                        type="text"
                        value={formData.lastName}
                        onChange={e => update({ lastName: e.target.value })}
                        className={inputStyles}
                    />
                    {errors.lastName && <p className={errorStyles}>{errors.lastName}</p>}
                </div>

                <div>
                    <label className={labelStyles}>Email</label>
                    <input
                        type="email"
                        value={formData.email}
                        onChange={e => update({ email: e.target.value })}
                        className={inputStyles}
                    />
                    {errors.email && <p className={errorStyles}>{errors.email}</p>}
                </div>

                <div>
                    <label className={labelStyles}>Phone</label>
                    <input
                        type="tel"
                        value={formData.phone}
                        onChange={e => update({ phone: e.target.value })}
                        className={inputStyles}
                    />
                    {errors.phone && <p className={errorStyles}>{errors.phone}</p>}
                </div>

                <div>
                    <label className={labelStyles}>Birthdate</label>
                    <input
                        type="date"
                        value={formData.birthDate}
                        onChange={e => update({ birthDate: e.target.value })}
                        className={inputStyles}
                    />
                    {errors.birthDate && <p className={errorStyles}>{errors.birthDate}</p>}
                </div>
            </div>

            <div className="flex gap-3 mt-8">
                <button onClick={back} className={buttonStyles.secondary}>Back</button>
                <button onClick={handleNext} className={buttonStyles.primary}>Next</button>
            </div>
        </div>
    )
}