import { useState } from 'react'
import { Group, FormData } from '../../utils/types.ts'
import { buttonStyles, labelStyles, errorStyles } from '../../utils/styles.ts'

interface StepOneProps {
  group: Group
  formData: FormData
  update: (fields: Partial<FormData>) => void
  next: () => void
}

export default function StepOne({ group, formData, update, next }: StepOneProps) {
    const [error, setError] = useState<string>('')

    const handleNext = () => {
        if (!formData.memberTypeId) {
            setError('Please select a type')
            return
        }
        setError('')
        next()
    }

    return (
        <div>
            <h1 className="text-2xl font-bold text-gray-800 mb-2">{group.title}</h1>
            <p className="text-gray-500 mb-6">{group.description}</p>

            <p className="text-sm text-blue-800 rounded-lg mb-6">
                Registration opens: {new Date(group.registrationOpens).toLocaleDateString('en-EN', {
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric'
                })}
            </p>

            <div className="mb-6">
                <label className={labelStyles}>Membertype</label>
                {group.memberTypes.map(type => (
                    <div
                        key={type.id}
                        onClick={() => update({ memberTypeId: type.id })}
                        className={`border rounded-lg p-4 mb-2 cursor-pointer transition
                            ${formData.memberTypeId === type.id
                                ? 'border-blue-600 bg-blue-50'
                                : 'border-gray-200 hover:border-blue-300'
                            }`}
                    >
                        <span className="font-medium text-gray-700">{type.name}</span>
                    </div>
                ))}
                {error && <p className={errorStyles}>{error}</p>}
            </div>

            <button onClick={handleNext} className={buttonStyles.primary}>
                Next
            </button>
        </div>
    )
}