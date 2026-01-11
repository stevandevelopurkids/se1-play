\## \[2.1.0] - 2026-03-25

\### Added

\- New dark mode feature for improved nighttime viewing (#2468)

\- API endpoint for exporting user data in compliance with GDPR (/api/v1/user/export)



\### Changed

\- Upgraded React.js to version 18.0 for improved performance (#3579)

\- Refactored database queries to optimize load times on the dashboard



\### Deprecated

\- Legacy authentication method using API keys (to be removed in v3.0)



\### Removed

\- Support for Internet Explorer 11 (#4321)



\### Fixed

\- Resolved race condition in concurrent user edits (#5432)

\- Corrected timezone handling for international users (#6543)



\### Security

\- Implemented rate limiting on login attempts to prevent brute force attacks

\- Updated bcrypt library to address potential vulnerability (CVE-2024-XXXX)

