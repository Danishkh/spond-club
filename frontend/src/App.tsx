import { useState, useEffect } from 'react'
import { Group } from './utils/types.ts'
import { getGroup } from './services/api.ts'
import Wizard from './components/wizard/Wizard.tsx'
import { errorStyles } from './utils/styles.ts'

const FORM_ID = 'B171388180BC457D9887AD92B6CCFC86'

function RegistrationClosedBanner({ group }: { group: Group }) {
    return (
        <div className="bg-red-50 border border-red-200 text-red-700 rounded-2xl p-8 max-w-2xl w-full text-center">
            <h2 className="text-xl font-bold mb-2">Registration is not active</h2>
            <p className="text-red-600">
                Registration for <span className="font-semibold">{group.title}</span> opens on{'  '}
                {new Date(group.registrationOpens).toLocaleDateString('en-GB', {
                    year: 'numeric', month: 'long', day: 'numeric'
                })}
            </p>
        </div>
    )
}

export default function App() {
    const [group, setGroup] = useState<Group | null>(null)
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        getGroup(FORM_ID)
            .then(setGroup)
            .catch(() => setError('Failed to load group'))
            .finally(() => setLoading(false))
    }, [])

    if (loading) return <div className="text-center p-8">Loading...</div>
    if (error) return <div className={errorStyles}>{error}</div>
    if (!group) return null

    const registrationIsOpen = new Date(group.registrationOpens) <= new Date()

    return (
        <div className="min-h-screen bg-gray-50 flex items-center justify-center p-4">
            {!registrationIsOpen ? (
                <RegistrationClosedBanner group={group} />
            ) : (
                <Wizard group={group} />
            )}
        </div>
    )
}